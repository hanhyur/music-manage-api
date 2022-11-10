package kr.co.study.api.common.engine.helper.path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description path helper
 **********************************************************************************************************************/
@Component
public class PathHelper {

	public static File getPath(String path) throws IOException {
		return new ClassPathResource(path).getFile();
	}

	public static String getRootPath(){
		return new FileSystemResource(File.separator).getFile().getAbsolutePath();
	}
}
