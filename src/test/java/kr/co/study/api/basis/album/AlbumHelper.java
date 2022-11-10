package kr.co.study.api.basis.album;

import kr.co.study.api.common.engine.test.TestHelper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

import static kr.co.study.api.basis.album.form.AlbumForm.Request;
import static kr.co.study.api.common.engine.helper.model.ObjectHelper.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album helper
 **********************************************************************************************************************/
public class AlbumHelper extends TestHelper {

    @SneakyThrows
    public static void getPage(Request.Find find) {
        mock.perform(get("/api/albums/pages")
                        .content(toJson(find)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    public static void get(Integer albumId) {
        mock.perform(get("/api/albums/{albumId}", albumId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    public static Integer add(Request.Add add) {
        return toInstance(Integer.class,
                mock.perform(post ("/api/albums")
                .content  (toJson(add)))
                .andExpect(status().isOk())
                .andDo    (print()));
    }

    @SneakyThrows
    public static Integer modify(Integer albumId, Request.Modify modify) {
        return toInstance(Integer.class,
                mock.perform(put("/api/albums/{albumId}", albumId)
                                .content(toJson(modify)))
                        .andExpect(status().isOk())
                        .andDo(print()));
    }

    @SneakyThrows
    public static void remove(Integer albumId) {
        mock.perform(delete("/api/albums/{albumId}", albumId))
                .andExpect (status().isOk())
                .andDo     (print());
    }

    public static Request.Find findAlbum() {
        return newInstance(Request.Find.class);
    }

    public static Request.Add addAlbum() {

        List<Request.Add.Soundtrack> soundtracks = new ArrayList<>();
        soundtracks.add(newInstance(Request.Add.Soundtrack.class));
        soundtracks.add(newInstance(Request.Add.Soundtrack.class));
        soundtracks.add(newInstance(Request.Add.Soundtrack.class));
        soundtracks.add(newInstance(Request.Add.Soundtrack.class));
        soundtracks.add(newInstance(Request.Add.Soundtrack.class));

        Request.Add add = newInstance(Request.Add.class);
        add.setArtist(Request.Artist.builder().id(1).build());
        add.setSoundtracks(soundtracks);

        return add;
    }

    public static Request.Modify modifyAlbum() {

        List<Request.Modify.Soundtrack> soundtracks = new ArrayList<>();
        soundtracks.add(newInstance(Request.Modify.Soundtrack.class));
        soundtracks.add(newInstance(Request.Modify.Soundtrack.class));
        soundtracks.add(newInstance(Request.Modify.Soundtrack.class));
        soundtracks.add(newInstance(Request.Modify.Soundtrack.class));
        soundtracks.add(newInstance(Request.Modify.Soundtrack.class));

        Request.Modify modify = newInstance(Request.Modify.class);
        modify.setArtist(Request.Artist.builder().id(1).build());
        modify.setSoundtracks(soundtracks);

        return modify;
    }

}
