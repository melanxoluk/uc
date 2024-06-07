import ApplicationController from "./ApplicationController.js";
import Application from "./Application.js";
import ConfigurationVersionController from "./ConfigurationVersionController.js";

const app = new Application();
const cvController = new ConfigurationVersionController(app);
cvController.mount(document.body)
