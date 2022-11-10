package kr.co.study.api.basis.album.adapter;

import kr.co.study.api.basis.album.entity.Album;
import kr.co.study.api.basis.album.service.AlbumService;
import kr.co.study.api.basis.artist.service.ArtistService;
import lombok.RequiredArgsConstructor;

/**
 * @since       2022.11.10
 * @author      yadon079
 * @description album adapter. This class is deprecated.
 **********************************************************************************************************************/
@RequiredArgsConstructor
public class AlbumAdapter {

    private final AlbumService albumService;

    private final ArtistService artistService;

    public Integer add(Album album) {
        artistService.get(album.getArtist());

        return albumService.add(album);
    }

    public Integer modify(Album album) {
        artistService.get(album.getArtist());

        return albumService.modify(album);
    }

}
