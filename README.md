Universal Configuration (uc) Юси
---
Command line tool для управления конфигурациями в event driven стиле  
Q1: как быть не с текстовыми файлами? .docx, .bpmn, .font, .png, ...  
A1: Подрубать minio? Делать zip?

Пример:  
configuration.yaml
```yaml
code: boooks
```
client.yaml
```yaml
local:
  url: http://localhost:9094
prod:
  url: https://boooks.app/kafka
```
uc deploy local
uc deploy local 1.2.3



- (docker) сервис для управления конфигурацией
- в event driven стиле
- с поддержкой миграций
- есть клиент для поддержки репозиториев конфигурации
- API для релизов
- версионность


репа -> client-config -> service(s)
структура репы
---
root dir=<project>-configuration
# как, зачем, нужно ли? может можно просто конфиги докручивать при изменении схем
# ./migrations 			- папка с миграциями конфигов при изменениях схемы
./configs 				- папка с конфигами
./schemas 				- папка с схемой конфигурации
./configuration.yaml	- папка с описанием конфигурации
# (continue) deployment support
./client.yaml 			- информация о сервисах на которые должны раскатиться конфигурации
./.env

язык=схема конфигурации: это модель данных конфигов
текст=конфигурация: это коллекции инстансов сущностей конфигурации + модель данных данных


API
---
the collection of the configurations and their revisions
/<name>/<semantic-version> - entry point for every entry for configuration? unified content of the configuration?
/<name>/<semantic-version>/<path> - part of the configuration
/<name> - develop version of configuration which is always in WIP status
POST /<name> - create new configuration in the service
PUT /<name> - update configuration content
POST /<name>/release - make revision for the actual develop configuration, supports RC


пока сложно и не нужно, попозже - нужно запустить сервис с конфигами в простом виде
nonstop linux services
---
process wich always listening configuration events with commands & configurations and not going dead on resource starvation, just drops logic execution & port listening. There is no problem to
change any part of the configuration

выглядит как Application servers. Отличается типом, видом конфигурирования. Изменения бизнес логики требует перезапуска? Как формировать ответ


Configuration
name
version
history
ConfigSchema[]
Config[]

это сервис для одновременной работы команд в нём на основе текстовых файлов в гите
основная проблема как подвязать интерфейс к гит репозиторию. Сделать его частью инструмента?
Сложно будет сделать онлайн версию, только для локальной разработки - хотя гитлаб как-то даёт
возможность сделать коммит на сайте - всё возможно. Хотя основным способом взаимодействия будет
локальный клиент, который будет стремиться к увеличению скорости работы пользователя

не нужно пытаться сопоставлять реализацию связей между сущностями на уровень СУБД, весь граф
хранить в памяти. Хотя выглядит интересной возможностью определить для сущности схемы флаг
перзистентности. Что инстансы схемы конфигурации являются описаниями хранимых данных

хотя так пропадёт возможность предоставить платформу для хранения таких данных. Тогда лучше
предоставить дефолтные не особо настраиваемые возможные схемы определения перзистентных
сущностей, чтобы их формат хоть и описывался в конфигурации, но имел всегда одинаковый формат,
а в платформу встраивать движок для работы с данными описаниями. Формат можно использовать тот
же, что и для определения схем конфигураций и тот же самый формат API для взаимодействия с ним.
Описание перзистентных сущностей называется Модель данных, если вдруг кто-то забыл

и тогда начинает размываться функциональность компонента, это конфиг или платормфа или ещё что-то.
Становится слишком сложно, поэтому перзистентность - это отдельный сервис, который работает на
основе юси. И потенциальный потребитель конфигов

курт репа <-- юси клиент <-- юси--event broker <-- курт
- что если юси будет виртуальной и все хранение сделать в кафке?
- непонятно как быть с релизами, чтобы получать конкретную версию асап
  uc.<config-mnemonic>
  uc.<config-mnemonic>.<(sem)ver>
  uc.<config-mnemonic>.1.2.0
  uc.<config-mnemonic>.1.20.1
  uc.<config-mnemonic>.1
  uc.<config-mnemonic>.2
  uc.<config-mnemonic>.102

uc.kurt # конфигурация самого сервиса
uc.app1 # конфигурация настраиваемого сервиса 1
uc.app2 # конфигурация настраиваемого сервиса 2
