package kr.co.study.api.common.attach.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.study.api.common.attach.form.AttachForm.Request;
import kr.co.study.api.common.attach.form.AttachForm.Response;
import kr.co.study.api.common.attach.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static kr.co.study.api.common.attach.mapper.AttachMapper.mapper;

/**
 * @since       2020.02.24
 * @author      lucas
 * @description attach controller
 **********************************************************************************************************************/
@Api(description="첨부")
@RestController
@RequiredArgsConstructor
@RequestMapping("${property.api.end-point}")
public class AttachController {

	private final AttachService attachService;

	@ApiOperation("등록")
	@PostMapping("/attaches")
	public Response.FindOne add(MultipartFile file){
		return mapper.toFindOne(attachService.add(file));
	}

	@ApiOperation("삭제")
	@DeleteMapping("/attaches")
	public void remove(@Valid Request.Remove remove){
		attachService.remove(mapper.toAttach(remove));
	}
}