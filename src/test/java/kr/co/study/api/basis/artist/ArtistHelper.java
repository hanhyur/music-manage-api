package kr.co.study.api.basis.artist;

import kr.co.study.api.basis.artist.form.ArtistForm.Request;
import kr.co.study.api.common.engine.test.TestHelper;
import lombok.SneakyThrows;

import static kr.co.study.api.common.engine.helper.model.ObjectHelper.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @since       2022.11.01
 * @author      aslan
 * @description artist helper
 **********************************************************************************************************************/
public class ArtistHelper extends TestHelper {

    @SneakyThrows
    public static void getPage(Request.Find find) {
        mock.perform(get("/api/artists/pages")
                        .content(toJson(find)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    public static void get(Integer artistId) {
        mock.perform(get("/api/artists/{artistId}", artistId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    public static Integer add(Request.Add add) {
        return toInstance(Integer.class, mock.perform(post ("/api/artists")
                .content  (toJson(add)))
                .andExpect(status().isOk())
                .andDo    (print()));
    }

    @SneakyThrows
    public static Integer modify(Integer artistId, Request.Modify modify) {
        return toInstance(Integer.class,
                mock.perform(put("/api/artists/{artistId}", artistId)
                                .content(toJson(modify)))
                        .andExpect(status().isOk())
                        .andDo(print()));
    }

    @SneakyThrows
    public static void remove(Integer artistId) {
        mock.perform(delete("/api/artists/{artistId}", artistId))
                .andExpect (status().isOk())
                .andDo     (print());
    }

    public static Request.Find findArtist() {
        return newInstance(Request.Find.class);
    }

    public static Request.Add addArtist() {
        return newInstance(Request.Add.class);
    }

    public static Request.Modify modifyArtist() {
        return newInstance(Request.Modify.class);
    }

}
