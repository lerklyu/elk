# Task Manager — ELK Stack

Проект демонстрирует сбор, обработку, хранение и визуализацию логов Spring Boot приложения с использованием ELK Stack.

## Используемые технологии

- Java 21, Spring Boot
- Logback — формирование логов в JSON
- Filebeat — сбор логов
- Logstash — обработка логов
- Elasticsearch — хранение логов
- Kibana — визуализация и мониторинг
- Docker Compose

## Структура проекта

- `elk/filebeat/filebeat.yml` — конфигурация Filebeat.
- `elk/logstash/logstash.conf` — конфигурация Logstash.
- `docker-compose-elk.yml` — запуск ELK Stack.
- `src/main/resources/logback-spring.xml` — настройка логирования.
- `logs/` — JSON-файлы логов приложения.
- `kibana/taskmanager-dashboard.ndjson` — экспорт Data View, визуализаций и Dashboard.

## Запуск

1. Запустить Elasticsearch, Logstash, Kibana и Filebeat:

```bash
docker compose -p taskmanager-elk -f docker-compose-elk.yml up -d
```

2. Запустить Spring Boot приложение.

3. Открыть Kibana:

http://localhost:5601

4. При необходимости импортировать настройки Kibana из файла `kibana/taskmanager-dashboard.ndjson` через **Stack Management → Saved Objects → Import**.

5. Открыть Dashboard `Task Manager Monitoring`.

## Результат

Настроен сбор логов приложения через Filebeat и Logstash с последующей передачей в Elasticsearch.

В Kibana создан Dashboard `Task Manager Monitoring`, содержащий:
- Количество логов по уровням (INFO, WARN, ERROR).
- Динамику поступления логов во времени.
