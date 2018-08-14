package com.didispace.scca.core.service;

import com.didispace.scca.core.domain.Env;
import com.didispace.scca.core.domain.Label;
import com.didispace.scca.core.domain.Project;
import org.springframework.cloud.config.environment.Environment;

import java.util.Map;
import java.util.Properties;

/**
 * 配置内容的持久化操作
 * <p>
 * <p>
 * Created by 程序猿DD/翟永超 on 2018/4/24.
 * <p>
 * Blog: http://blog.didispace.com/
 * Github: https://github.com/dyc87112/
 */
public interface PersistenceService {

    /**
     * 获取某个配置文件
     *
     * @param application - 应用名称
     * @param profile     - 环境名称
     * @param label       - 版本名称
     * @return
     */
    Properties readProperties(String application, String profile, String label);

    /**
     * 根据环境配置获取某个配置文件
     *
     * @param env         - 环境配置
     * @param application - 应用名称
     * @param profile     - 环境名称
     * @param label       - 版本名称
     * @return
     * @autor David.Liu
     */
    Map<String, Object> readProperties(Environment env, String application, String profile, String label);

    /**
     * 删除某个环境下所有项目所有版本的配置文件
     *
     * @param env
     */
    void deletePropertiesByEnv(Env env);

    /**
     * 删除某个项目，所有版本在所有环境下的所有配置文件
     *
     * @param project
     */
    void deletePropertiesByProject(Project project);

    /**
     * 删除某个项目某个环境下的所有配置文件
     *
     * @param project
     * @param env
     */
    void deletePropertiesByProjectAndEnv(Project project, Env env);

    /**
     * 删除某个项目某个版本下所有的配置文件
     * <p>
     * （由于Label只属于一个项目，所以该操作就是删除某个项目某个版本在所有环境下的配置）
     *
     * @param label
     */
    void deletePropertiesByLabel(Label label);

    /**
     * 删除某个配置文件
     *
     * @param application - 应用名称
     * @param profile     - 环境名称
     * @param label       - 版本名称
     */
    void deleteProperties(String application, String profile, String label);

    /**
     * 保存某个配置文件
     *
     * @param application - 应用名称
     * @param profile     - 环境名称
     * @param label       - 版本名称
     * @param update      - 更新的全量配置内容
     */
    void saveProperties(String application, String profile, String label, Properties update);

    /**
     * 修改环境名之后需要操作的配置持久化内容
     *
     * @param oldName - 原环境名
     * @param newName - 新环境名
     */
    void updateProfileName(String oldName, String newName);

}
