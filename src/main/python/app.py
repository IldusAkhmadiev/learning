from flask import Flask, request, jsonify
from pydub import AudioSegment
from transformers import WhisperProcessor, WhisperForConditionalGeneration
import torch
import os
import tempfile

app = Flask(__name__)

# Загрузка модели и процессора
processor = WhisperProcessor.from_pretrained("openai/whisper-tiny")
model = WhisperForConditionalGeneration.from_pretrained("openai/whisper-tiny")

# Функция для разделения аудиофайла на сегменты
def split_audio(audio, segment_duration_ms):
    return [audio[i:i + segment_duration_ms] for i in range(0, len(audio), segment_duration_ms)]

# Функция для преобразования в mp3
def convert_to_mp3(input_path):
    output_path = os.path.splitext(input_path)[0] + ".mp3"
    audio = AudioSegment.from_file(input_path)
    audio.export(output_path, format="mp3")
    return output_path

@app.route('/transcribe', methods=['POST'])
def transcribe_audio():
    if 'file' not in request.files:
        return jsonify({"error": "No file provided"}), 400

    audio_file = request.files['file']

    # Создаем временный файл для обработки
    with tempfile.NamedTemporaryFile(delete=False, suffix=".wav") as temp_audio_file:
        audio_file.save(temp_audio_file.name)
        temp_audio_path = temp_audio_file.name

    try:
        # Преобразование файла в mp3
        audio_path = convert_to_mp3(temp_audio_path)

        # Загрузка и обработка аудиофайла
        audio = AudioSegment.from_file(audio_path).set_frame_rate(16000).set_channels(1)

        segment_duration_ms = 30000  # Длительность сегмента (30 секунд)
        segments = split_audio(audio, segment_duration_ms)

        # Распознавание текста из каждого сегмента
        full_transcription = []
        for segment in segments:
            audio_array = torch.FloatTensor(segment.get_array_of_samples()) / 32768.0  # Нормализация

            inputs = processor(
                audio_array.numpy(),
                sampling_rate=16000,
                return_tensors="pt",
                return_attention_mask=True
            )
            input_features = inputs.input_features
            attention_mask = inputs.attention_mask

            # Транскрипция текста из аудио
            predicted_ids = model.generate(input_features, attention_mask=attention_mask)
            transcription = processor.batch_decode(predicted_ids, skip_special_tokens=True)

            full_transcription.append(transcription[0])

        # Объединяем все сегменты в один текст
        final_text = " ".join(full_transcription)

        return jsonify({"result": "success", "transcription": final_text})

    except Exception as e:
        return jsonify({"error": str(e)}), 500

    finally:
        # Удаляем временные файлы
        if os.path.exists(temp_audio_path):
            os.remove(temp_audio_path)
        if os.path.exists(audio_path):
            os.remove(audio_path)

if __name__ == '__main__':
    app.run()