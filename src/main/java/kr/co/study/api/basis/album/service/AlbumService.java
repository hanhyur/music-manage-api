package kr.co.study.api.basis.album.service;

import kr.co.study.api.basis.album.entity.Album;
import kr.co.study.api.basis.album.form.AlbumForm.Request.Find;
import kr.co.study.api.basis.album.repository.AlbumRepository;
import kr.co.study.api.basis.album.validation.AlbumValidation;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import kr.co.study.api.common.engine.exception.BadRequestException;
import kr.co.study.api.common.engine.exception.common.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album service
 **********************************************************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    private final AlbumValidation albumValidation;

    @Transactional(readOnly = true)
    public Paging<Album> getPage(Find find, Pageable page) {
        return albumRepository.getPage(find, page);
    }

    @Transactional(readOnly = true)
    public Album get(Album album) {
        return albumRepository.get(album).orElseThrow(() ->
                new BadRequestException(ExceptionCode.E00110002));
    }

    public Integer add(Album album) {
        albumValidation.addValidate(album);
        return albumRepository.add(album);
    }

    public Integer modify(Album album) {
        albumValidation.modifyValidate(album);
        return albumRepository.modify(album);
    }

    public void remove(Album album) {
        get(album);
        albumRepository.remove(album);
    }

}
