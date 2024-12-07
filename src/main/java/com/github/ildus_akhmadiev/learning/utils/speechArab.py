import sys
import json
from pydub import AudioSegment
from transformers import WhisperProcessor, WhisperForConditionalGeneration
import torch
import os

# Загрузка модели и процессора
processor = WhisperProcessor.from_pretrained("openai/whisper-large-v3-turbo")
model = WhisperForConditionalGeneration.from_pretrained("openai/whisper-large-v3-turbo")


# Указание языка (арабский)
language = "ar"

# Функция для разделения аудиофайла на сегменты
def split_audio(audio, segment_duration_ms):
    return [audio[i:i + segment_duration_ms] for i in range(0, len(audio), segment_duration_ms)]

# Функция для преобразования в mp3
def convert_to_mp3(input_path):
    output_path = os.path.splitext(input_path)[0] + ".mp3"
    audio = AudioSegment.from_file(input_path)
    audio.export(output_path, format="mp3")
    return output_path

# Проверка аргументов командной строки
if len(sys.argv) < 3:
    print("Ошибка: необходимо указать имя файла для сохранения результата и путь к аудиофайлу.")
    sys.exit(1)

output_file = sys.argv[1]  # Путь для записи результата
audio_path = sys.argv[2]   # Путь к аудиофайлу для обработки

# Убедитесь, что путь output_file существует
output_dir = os.path.dirname(output_file)
if output_dir and not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Принудительное преобразование входного файла в mp3
try:
    print("Преобразование файла в формат MP3...")
    audio_path = convert_to_mp3(audio_path)
    print(f"Файл успешно преобразован: {audio_path}")
except Exception as e:
    print(f"Ошибка при преобразовании файла в MP3: {e}")
    sys.exit(1)

# Загрузка и обработка аудиофайла
try:
    audio = AudioSegment.from_file(audio_path).set_frame_rate(16000).set_channels(1)  # Конвертация в моно и 16 кГц
except Exception as e:
    print(f"Ошибка при загрузке аудиофайла: {e}")
    sys.exit(1)

segment_duration_ms = 30000  # Длительность сегмента (30 секунд)
segments = split_audio(audio, segment_duration_ms)

# Распознавание текста из каждого сегмента
full_transcription = []
for i, segment in enumerate(segments):
    audio_array = torch.FloatTensor(segment.get_array_of_samples()) / 32768.0  # Нормализация

    inputs = processor(
        audio_array.numpy(),
        sampling_rate=16000,
        return_tensors="pt",
        language=language,
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

# Запись результата в JSON
try:
    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump({"result": "success", "value": final_text}, f, ensure_ascii=False)
    print(f"Результат сохранен в файл: {output_file}")
except Exception as e:
    print(f"Ошибка при записи результата в файл: {e}")
    sys.exit(1)
