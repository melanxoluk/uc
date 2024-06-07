/**
 Root context object which would be passed for application controllers hierarchy
 to support everything except rendering & managing user interactions
 */
class Application {
  constructor() {
    this.api = new UiApi();
  }
  
  route(path) {
    /*
    todo: не соображу как это запилить, отложу на попозже
     пока сделаю форму рендера конфигурации 
    app could be started on any uri and application should build
    correct state according uri
    
    1. Parse actual uri
    2. Parser should know hierarchy or matching for actual
    3. 
    
    /schemas - list of schemas
    /schemas/{name} - schema details
    /schemas/{name}/{version} - schema navigator
    /schemas/{name}/settings - schemas settings
    
    /configurations - list of configurations
    /configurations/{name} - configurations directory
    /configurations/{name}/{version} - configuration navigator
    /configuration/{name}/settings
    
    it is possible that routing would be continued in child component
    resolved should least defined route, because
     */
    
    /*
    Как управлять какие данные когда подгружаются? 
    
    Идеально, чтобы все инициализировалось при запуске сайта минимальным 
    количеством запросов. Или верстка должна уметь выстроиться и без данных?
    И при их появлении просто довыстроить себя
    
    Запросы данных не должны блокировать отображение структуры и препятствовать
    возможной навигации и взаимодействию 
    
    Есть нарративы, что на таких то страницах нужны такие данные
    Что должна отображаться загрузка на этих страницах, пока данные не готовы
    То есть это кто-то выше контроллеров? Он знает, что странице нужны будут 
    какие данные, которые потом этот контроллер отобразит
     */
  }
}

export default Application;
