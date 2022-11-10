package kr.co.study.api.basis.album.validation;

import kr.co.study.api.basis.album.entity.Album;
import kr.co.study.api.basis.album.entity.Soundtrack;
import kr.co.study.api.basis.album.repository.AlbumRepository;
import kr.co.study.api.basis.artist.entity.Artist;
import kr.co.study.api.basis.artist.repository.ArtistRepository;
import kr.co.study.api.common.engine.exception.BadRequestException;
import kr.co.study.api.common.engine.exception.common.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @since       2022.11.10
 * @author      yadon079
 * @description album validation
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class AlbumValidation {

    private final AlbumRepository albumRepository;

    private final ArtistRepository artistRepository;

    public void addValidate(Album album) {
        artistValid(album.getArtist());
    }

    public void modifyValidate(Album album) {
        artistValid(album.getArtist());
        Album albumDb = findAlbumOrElseThrow(album);
        soundtrackValid(album, albumDb);
    }

    private void soundtrackValid(Album albumOrigin, Album albumDB) {
        List<Soundtrack> soundtracks = albumOrigin.getSoundtracks().stream()
                .filter(i -> i.getId() != null && i.getId() != 0 && isMatched(albumDB, i))
                .collect(Collectors.toList());

        if (soundtracks.size() > 0) {
            throw new BadRequestException(ExceptionCode.E00110003);
        }
    }

    private boolean isMatched(Album albumDB, Soundtrack st) {
        return albumDB.getSoundtracks().stream()
                .map(Soundtrack::getId)
                .collect(Collectors.toList()).contains(st.getId());
    }

    private Album findAlbumOrElseThrow(Album album) {
        return albumRepository.get(album).orElseThrow(
                () -> new BadRequestException(ExceptionCode.E00110002)
        );
    }

    private void artistValid(Artist artist) {
        artistRepository.get(artist);
    }

}
