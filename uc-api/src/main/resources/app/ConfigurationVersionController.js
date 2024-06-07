class ConfigurationVersionController {
  constructor(app) {
    this.app = app;
  }
  
  mount() {
    this.setLoading(true);
    this.app.api
      .fetchConfigurationVersion("first", "1.0.0")
      .then(ver => this.setConfigurationVersion(ver))
      .catch();
  }
  
  setConfigurationVersion(ver) {
    this.setLoading(false)
    // set configuration name
    // set configuration version
    // set configuration files tree
  }
  
  setLoading(flag) {
    // show page loading
  }
  
  view() {
    // todo use mythrill here?
  }
}

export default ConfigurationVersionController;
