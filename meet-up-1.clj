(ns clojure-test.core)

(def data {:RTDataId "rt-main-values-device",
           :CardProjectionType 9,
           :Keyword "live-values-items",
           :CardProperties [{:Parameter {:Name "Aggregated Strings Availability",
                                         :Unit "%"},
                             :Value {:Value "99.88", :Date "2018-10-01T00:00:00"},
                             :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076",
                             :TypeKey "8537e625-59cd-537f-93ad-c90b17e32036",
                             :DisplayName "Aggregated Strings Availability",
                             :DataSourceId 6871}
                            {:Parameter {:Name "Assigned Insolation",
                                         :Unit "kWh/m2"},
                             :Value {:Value 5.244289227216684,
                                     :Date "2018-10-02T15:08:34"},
                             :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:71",
                             :TypeKey "fd69c2e1-3372-5a48-a51a-24261703561c",
                             :DisplayName "Assigned Insolation",
                             :DataSourceId 2165}
                            {:Parameter {:Name "Assigned Irradiance",
                                         :Unit "W/m2"},
                             :Value {:Value 601.030029296875,
                                     :Date "2018-10-02T15:08:34"},
                             :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70",
                             :TypeKey "3564134b-4cab-5757-98ff-4ff8d48deac6",
                             :DisplayName "Assigned Irradiance",
                             :DataSourceId 2162}
                            {:Parameter {:Name "Availability",
                                         :Unit "%"},
                             :Value {:Value 100, :Date "2018-10-01T00:00:00"},
                             :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:73",
                             :TypeKey "8c4b0e05-6665-5c72-9d94-747a4c6ff0bb",
                             :DisplayName "Availability", :DataSourceId 2186}
                            {:Parameter {:Name "Comm Status", :Unit "%"},
                             :Value {:Value 0, :Date "2018-10-02T15:08:36"},
                             :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:74",
                             :TypeKey "8e95d579-c7f7-50d7-a00a-7fbe6e5b0f4e",
                             :DisplayName "Comm Status", :DataSourceId 41}],
           :DisplayName "Live values items"})

(def command [{:Value {:Value "Pepito"}
               :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076"}
              {:Value {:Date "koko"}
               :Id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70"}])

(defn generateNewCard [dataItem command]
  (let [foundElement (first (filter #(= (:Id dataItem) (:Id %)) command))
        itemKey (first (keys (:Value foundElement)))
        itemValue (first (vals (:Value foundElement)))]
    (if foundElement
      (assoc-in dataItem [:Value itemKey] itemValue)
      dataItem)))

(defn program [data command]
  (let [cardProperties (:CardProperties data)
        newCardProperties (map #(generateNewCard % command) cardProperties)]
    (assoc data :CardProperties newCardProperties)))

(program data command)
