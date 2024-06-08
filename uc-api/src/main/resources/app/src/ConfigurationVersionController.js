class ConfigurationVersionController {
  constructor(app) {
    this.app = app;
    this.dirs = ["first", "second"];
  }

  mount(parent) {
    m.render(parent, this.view())
    $('.ui.accordion').accordion({exclusive: false});
    setTimeout(() => {
      this.dirs = ["first", "second", "third"];
      console.info("updated");
      m.render(parent, this.view());
    }, 3000)
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
    return m("div", {class: "ui masthead vertical segment"},
      m("div", {class: "ui container"}, [

        m("h1", {class: "ui header"}, [
          "Configuration",
          m("div", {class: "sub header"}, "v1.0.0")
        ]),

        m("div", {class: "ui small basic buttons"}, [
          m("button", {class: "ui button"}, [
            m("i", {class: "icon folder"}),
            "Add directory"
          ]),
          m("button", {class: "ui button"}, [
            m("i", {class: "icon file"}),
            "Add file"
          ]),
        ]),

        m("div", {class: "ui segment"},
          m("div", {class: "ui tree accordion"},
            this.dirs.flatMap(dir => {
              return [
                m("div", {class: "title"}, [
                  m("i", {class: "folder icon"}),
                  dir
                ]),
                m("div", {class: "content"}, [
                    m("p", {class: "transition hidden"}, [
                      m("i", {class: "icon file"}),
                      dir
                    ])
                  ]
                )
              ]
            }))
        )
      ]))
  }
}

export default ConfigurationVersionController;
