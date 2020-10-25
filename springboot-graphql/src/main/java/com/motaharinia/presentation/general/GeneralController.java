package com.motaharinia.presentation.general;


import com.motaharinia.business.service.etcitem.EtcItemService;
import com.motaharinia.msutility.json.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GeneralController {

    private final EtcItemService etcItemService;

    @Autowired
    public GeneralController(EtcItemService etcItemService) {
        this.etcItemService = etcItemService;
    }

    @GetMapping("/getCombo")
    HashMap<String, ArrayList> getCombo(@RequestParam(name = "post") Optional<String> post) throws Exception {

        //تبدیل جیسون به هش مپ HashMap<String, HashMap<String, Object>>
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        HashMap<String, HashMap<String, Object>> initialHashMap = customObjectMapper.readValue(post.get(), HashMap.class);

        String inputId, entityName, restriction = "", parametersMode;
        HashMap<String, Object> valueHashMap;

        String requestServletPathUrl = "";
        Integer restrictionIntValue = null;
        Object paramId1;
        HashMap<String, ArrayList> returnHashmap = new HashMap<String, ArrayList>();
        for (Map.Entry<String, HashMap<String, Object>> entry : initialHashMap.entrySet()) {

            List<Object[]> listObject = new ArrayList<>();
            ArrayList listArrayObject = new ArrayList();
            inputId = entry.getKey();
            valueHashMap = entry.getValue();

            entityName = (String) valueHashMap.get("entity");
            parametersMode = (String) valueHashMap.get("parametersMode");
            Object value = valueHashMap.get("value");
            paramId1 = valueHashMap.get("paramId1");
            Object param1 = valueHashMap.get("param1");

            String type;

            switch (entityName) {
                case "etcItem":
                    switch (parametersMode) {
                        case "gender":
                            listObject = etcItemService.combo(parametersMode,false,false);
                            break;
                    }

            }
            if (listObject != null) {
                listArrayObject.addAll(listObject);
                listObject.stream().forEach(item -> System.out.println("item ======== " + item[0] +" , "+ item[1]));
            } else {
            }
            returnHashmap.put(inputId, listArrayObject);

        }
        return returnHashmap;

    }


}
