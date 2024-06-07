class ConfigurationVersionController {
  constructor(app) {
    this.app = app;
  }

  mount(parent) {
    m.mount(parent, this)

    // todo
    /*this.app.api
      .fetchConfigurationVersion("first", "1.0.0")
      .then(ver => this.setConfigurationVersion(ver))
      .catch();*/
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
    return m("div", {class:"ui container"}, [
      m("h1", {class: "ui header"}, [
        "Configuration",
        m("div", {class: "sub header"}, "v1.0.0")
      ]),
      
    ])
  }
}

export default ConfigurationVersionController;
