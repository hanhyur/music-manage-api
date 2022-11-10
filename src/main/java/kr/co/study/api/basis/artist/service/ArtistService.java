package kr.co.study.api.basis.artist.service;

import kr.co.study.api.basis.artist.entity.Artist;
import kr.co.study.api.basis.artist.form.ArtistForm.Request.Find;
import kr.co.study.api.basis.artist.repository.ArtistRepository;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import kr.co.study.api.common.engine.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since       2022.10.27
 * @author      aslan
 * @description artist service
 **********************************************************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public Paging<Artist> getPage(Find find, Pageable page) {
        return artistRepository.getPage(find, page);
    }

    @Transactional(readOnly = true)
    public Artist get(Artist artist) {
        return artistRepository.get(artist)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Integer add(Artist artist) {
        return artistRepository.add(artist);
    }

    public Integer modify(Artist artist) {
        get(artist);

        return artistRepository.modify(artist);
    }

    public void remove(Artist artist) {
        get(artist);

        artistRepository.remove(artist);
    }

}
