# Qa-Guru Kotlin Pet Project - Makefile
# Backend: http://localhost:1111 | Frontend: http://localhost:4000

.PHONY: help init build-backend build-frontend build run-backend run-frontend run restart stop-backend stop-frontend stop down test

# По умолчанию — показать справку
help:
	@echo "Qa-Guru Kotlin Pet Project"
	@echo ""
	@echo "Инициализация:"
	@echo "  make init           - Инициализировать submodules"
	@echo ""
	@echo "Сборка (build):"
	@echo "  make build-backend   - Собрать backend (Gradle)"
	@echo "  make build-frontend  - Собрать frontend (npm run build)"
	@echo "  make build           - Собрать backend и frontend"
	@echo ""
	@echo "Запуск (run):"
	@echo "  make run-backend    - Запустить backend (Docker, порт 1111)"
	@echo "  make run-frontend   - Запустить frontend (Docker, порт 4000)"
	@echo "  make run            - Запустить backend и frontend"
	@echo "  make restart        - Остановить контейнеры и запустить заново"
	@echo ""
	@echo "Остановка:"
	@echo "  make stop-backend   - Остановить backend"
	@echo "  make stop-frontend  - Остановить frontend"
	@echo "  make stop           - Остановить backend и frontend"
	@echo "  make down           - То же что make stop (остановить контейнеры)"
	@echo ""
	@echo "Тесты:"
	@echo "  make test           - Запустить тесты (Gradle)"

# --- Инициализация ---
init:
	git submodule update --init --recursive

# --- Сборка ---
build-backend:
	cd src/main/backend && ./gradlew build -x test

build-frontend:
	cd src/main/web && npm install && npm run build

build: build-backend build-frontend

# --- Запуск (Docker) ---
run-backend:
	cd src/main/backend && docker compose up -d

run-frontend:
	cd src/main/web && docker build -t romsper/testing-playground-frontend:latest . && docker compose up -d

run: stop run-backend run-frontend
	@echo ""
	@echo "Backend:  http://localhost:1111"
	@echo "Frontend: http://localhost:4000"

restart: run

# --- Остановка ---
stop-backend:
	cd src/main/backend && docker compose down

stop-frontend:
	cd src/main/web && docker compose down

stop: stop-backend stop-frontend

down: stop

# --- Тесты ---
test:
	./gradlew test
