HashMap<String, Object> pipelineParameters = new HashMap<>()

// Мастер пока используется для Фора банка + sup/1.02.01 и DQADC-1.02.01. Образы выкладываются автоматом на registry-ext.diasoft.ru (в будущем в планах в мастере сделать общую версию для Форы и Дом РФ банка)
if (BRANCH_NAME.equals("master") || BRANCH_NAME.contains("1.02.01")) {
    pipelineParameters.put("devops_project_1", "foradqmp")
    pipelineParameters.put("devops_instance_1", "")
    pipelineParameters.put("devops_stage_1", "dev")    

//Стенд клиента
    pipelineParameters.put("devops_project_2", "foradqmp")
    pipelineParameters.put("devops_instance_2", "null")
    pipelineParameters.put("devops_make_image_without_cluster_2", "true")
    pipelineParameters.put("devops_image_use_latest_2", "false")
    pipelineParameters.put("devops_image_make_properties_2", "false")
    pipelineParameters.put("devops_stage_2", "uat")
    pipelineParameters.put("devops_registry_stage_2", "uat")
}

// Сборка для Дом РФ банка + образы выкладываются автоматом на registry-ext.diasoft.ru
if (BRANCH_NAME.contains("1.01.01")) {
    pipelineParameters.put("devops_project_1", "dqmarketman")
    pipelineParameters.put("devops_instance_1", "")
    pipelineParameters.put("devops_stage_1", "dev")    

//Стенд клиента
    pipelineParameters.put("devops_project_2", "domrfdqmp")
    pipelineParameters.put("devops_instance_2", "null")
    pipelineParameters.put("devops_make_image_without_cluster_2", "true")
    pipelineParameters.put("devops_image_use_latest_2", "false")
    pipelineParameters.put("devops_image_make_properties_2", "false")
    pipelineParameters.put("devops_stage_2", "dev")
    pipelineParameters.put("devops_registry_stage_2", "dev")
}
dsMsaPipeline(pipelineParameters)