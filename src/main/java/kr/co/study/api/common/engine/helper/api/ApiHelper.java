package kr.co.study.api.common.engine.helper.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import kr.co.study.api.common.engine.constant.Constant;
import kr.co.study.api.common.engine.helper.compress.CompressHelper;
import kr.co.study.api.common.engine.helper.path.PathHelper;
import kr.co.study.api.common.engine.helper.property.PropertyHelper;
import lombok.SneakyThrows;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @since       2020.03.09
 * @author      lucas
 * @description api helper
 **********************************************************************************************************************/
@Component
public class ApiHelper {

    private static Environment environment;

    private final static String DIR_TEMP   = "temps";
    private final static String DIR_CLASS  = "WEB-INF/classes";
    private final static String NAME_CLASS = "class";

    @Autowired
    public ApiHelper(Environment environment){
        ApiHelper.environment = environment;
    }

    @SneakyThrows
    public static List<Api> getApis() {

        if(   ArrayUtils.contains(environment.getActiveProfiles(), "dev" )
            ||ArrayUtils.contains(environment.getActiveProfiles(), "prod")){
            return getApisOfDevOrProd();
        }
        return getApisOfDefault();
    }

    @SneakyThrows
    public static List<Api> getApisOfDefault() {
        final String rootPath = StringUtils.join(PathHelper.getPath(File.separator).getParent(), File.separator + "classes" + File.separator);
        return getApis(rootPath);
    }

    @SneakyThrows
    public static List<Api> getApisOfDevOrProd() {
        String targetPath = System.getProperty("user.dir");
        String warPath    = PropertyHelper.getProperty().getName();
        File warFile    = Paths.get(warPath   ).toFile();
        File tempFile   = Paths.get(targetPath).resolve(DIR_TEMP).toFile();

        CompressHelper.unzip(warFile, tempFile, Charsets.UTF_8.name());
        String rootPath = Paths.get(targetPath).resolve(DIR_TEMP).resolve(DIR_CLASS).toString() + File.separator;
        List<Api> apis  = getApis(rootPath);
        FileUtils.deleteQuietly(tempFile);
        return apis;
    }

    @SneakyThrows
    private static List<Api> getApis(String rootPath) {

        List<Class> classes  = Lists.newArrayList();
        for (File file : FileUtils.listFiles(FileUtils.getFile(rootPath), ImmutableList.of(NAME_CLASS).stream().toArray(String[]::new) , Boolean.TRUE)) {
            String packages = file.getAbsolutePath();
            packages = StringUtils.replace(packages, rootPath, Constant.String.EMPTY);
            packages = StringUtils.substring(packages, Constant.Integer.ZERO, StringUtils.indexOf(packages, Constant.String.DOT));
            packages = StringUtils.replace(packages, File.separator, Constant.String.DOT);

            Class clazz = Class.forName(packages);
            if (Objects.nonNull(clazz.getAnnotation(RestController.class))) {
                classes.add(clazz);
            }
        }

        List<Api> apis = Lists.newArrayList();
        for (Class clazz : classes) {
            for (Method method : Arrays.asList(clazz.getMethods())) {
                for (Annotation annotation : method.getAnnotations()) {
                    if(annotation instanceof GetMapping || annotation instanceof PostMapping || annotation instanceof PutMapping || annotation instanceof DeleteMapping || annotation instanceof PatchMapping){
                        apis.add(getApi(clazz, annotation));
                    }
                }
            }
        }

        return apis;
    }

    private static Api getApi(Class clazz, Annotation annotation) {
        Annotation requestMapping = annotation.annotationType().getDeclaredAnnotation(RequestMapping.class);
        return Api.builder()
                .clazz(clazz)
                .method(HttpMethod.resolve(RequestMapping.class.cast(requestMapping).method()[Constant.Integer.ZERO].name()))
                .uri(StringUtils.join(PropertyHelper.getProperty().getApi().getEndPoint(), String[].class.cast(AnnotationUtils.getValue(annotation))[Constant.Integer.ZERO]))
                .build();
    }
}
