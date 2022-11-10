package kr.co.study.api.basis.artist.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.study.api.basis.artist.form.ArtistForm.Request;
import kr.co.study.api.basis.artist.form.ArtistForm.Response;
import kr.co.study.api.basis.artist.service.ArtistService;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kr.co.study.api.basis.artist.mapper.ArtistMapper.*;

/**
 * @since       2022.10.27
 * @author      aslan
 * @description artist controller
 **********************************************************************************************************************/
@Api(description = "아티스트")
@RestController
@RequiredArgsConstructor
@RequestMapping("${property.api.end-point}")
public class ArtistController {

    private final ArtistService artistService;

    @ApiOperation("페이지")
    @GetMapping("/artists/pages")
    public Paging<Response.FindAll> getPage(@Valid Request.Find find, Pageable page) {
        return artistService.getPage(find, page).map(mapper::toFindAll);
    }

    @ApiOperation("조회")
    @GetMapping("/artists/{artistId}")
    public Response.FindOne get(@PathVariable Integer artistId) {
        return mapper.toFindOne(artistService.get(mapper.toArtist(artistId)));
    }

    @ApiOperation("등록")
    @PostMapping("/artists")
    public Integer add(@Valid @RequestBody Request.Add add) {
        return artistService.add(mapper.toArtist(add));
    }

    @ApiOperation("수정")
    @PutMapping("/artists/{artistId}")
    public Integer modify(@PathVariable Integer artistId,
                          @Valid @RequestBody Request.Modify modify) {
        return artistService.modify(mapper.toArtist(artistId, modify));
    }

    @ApiOperation("삭제")
    @DeleteMapping("/artists/{artistId}")
    public void remove(@PathVariable Integer artistId) {
        artistService.remove(mapper.toArtist(artistId));
    }

}
