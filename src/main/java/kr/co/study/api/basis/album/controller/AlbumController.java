package kr.co.study.api.basis.album.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.study.api.basis.album.adapter.AlbumAdapter;
import kr.co.study.api.basis.album.service.AlbumService;
import kr.co.study.api.basis.album.form.AlbumForm;
import kr.co.study.api.basis.album.mapper.AlbumMapper;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album controller
 **********************************************************************************************************************/
@Api(description = "앨범")
@RestController
@RequiredArgsConstructor
@RequestMapping("${property.api.end-point}")
public class AlbumController {

    private final AlbumService albumService;

    private final AlbumAdapter albumAdapter;

    @ApiOperation("페이지")
    @GetMapping("/albums/pages")
    public Paging<AlbumForm.Response.FindAll> getPage(@Valid AlbumForm.Request.Find find, Pageable page) {
        return albumService.getPage(find, page).map(AlbumMapper.mapper::toFindAll);
    }

    @ApiOperation("조회")
    @GetMapping("/albums/{albumId}")
    public AlbumForm.Response.FindOne get(@PathVariable Integer albumId) {
        return AlbumMapper.mapper.toFindOne(albumService.get(AlbumMapper.mapper.toAlbum(albumId)));
    }

    @ApiOperation("등록")
    @PostMapping("/albums")
    public Integer add(@Valid @RequestBody AlbumForm.Request.Add add) {
        return albumAdapter.add(AlbumMapper.mapper.toAlbum(add));
    }

    @ApiOperation("수정")
    @PutMapping("/albums/{albumId}")
    public Integer modify(@PathVariable Integer albumId,
                          @Valid @RequestBody AlbumForm.Request.Modify modify) {
        return albumAdapter.modify(AlbumMapper.mapper.toAlbum(albumId, modify));
    }

    @ApiOperation("삭제")
    @DeleteMapping("/albums/{albumId}")
    public void remove(@PathVariable Integer albumId) {
        albumService.remove(AlbumMapper.mapper.toAlbum(albumId));
    }

}
