package kr.co.study.api.common.attach.service;

import com.google.common.io.Files;
import kr.co.study.api.common.attach.entity.Attach;
import kr.co.study.api.common.engine.constant.Constant;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

/**   
 * @since       2020.02.24
 * @author      lucas
 * @description attach service
 **********************************************************************************************************************/
@Service
public class AttachService {

	@Value("${spring.servlet.multipart.location}")
	private String tempLocation;

	@Value("${property.attach.store-location}")
	private String storeLocation;

	private static Path tmpPath;
	private static Path storePath;

	@SneakyThrows
	public void initialize() {
		tmpPath   = Paths.get(tempLocation );
		storePath = Paths.get(storeLocation);

		FileUtils.forceMkdir    (tmpPath.toFile());
		FileUtils.forceMkdir    (storePath.toFile());
		FileUtils.cleanDirectory(tmpPath.toFile());
	}

	@SneakyThrows
	public Attach add(MultipartFile multipartFile) {

		LocalDate now       = LocalDate.now();
		String name      = StringUtils.join(UUID.randomUUID().toString(), Constant.String.DOT, Files.getFileExtension(multipartFile.getOriginalFilename()));
		Path path      = Paths.get(String.valueOf(now.getYear())).resolve(String.valueOf(now.getMonthValue())).resolve(String.valueOf(now.getDayOfMonth()));
		Path childPath = storePath.resolve(path);

		FileUtils.forceMkdir(childPath.toFile());
		multipartFile.transferTo(childPath.resolve(name).toFile());

		return Attach.builder().path(path.toString()).name(name).build();
	}

	@SneakyThrows
	public void remove(Attach attach) {
		FileUtils.deleteQuietly(storePath.resolve(attach.getPath()).resolve(attach.getName()).toFile());
	}
}