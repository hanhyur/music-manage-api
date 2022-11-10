package kr.co.study.api.common.attach;

import kr.co.study.api.common.attach.form.AttachForm.Request;
import kr.co.study.api.common.attach.form.AttachForm.Response;
import kr.co.study.api.common.engine.test.TestHelper;
import lombok.SneakyThrows;

import static kr.co.study.api.common.engine.helper.model.ObjectHelper.newInstance;
import static kr.co.study.api.common.engine.helper.model.ObjectHelper.toInstance;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @since       2020.02.25
 * @author      lucas
 * @description attach helper
 **********************************************************************************************************************/
public class AttachHelper extends TestHelper {

    @SneakyThrows
    public static Response.FindOne add(String path) {
        return toInstance(Response.FindOne.class,
                mock.perform(file("/api/attaches", path))
                        .andExpect(status().isOk())
                        .andDo    (print())
        );
    }

    @SneakyThrows
    public static void remove(Request.Remove remove) {
        mock.perform(delete("/api/attaches")
                .param("path", remove.getPath())
                .param("name", remove.getName()))
                .andExpect(status().isOk())
                .andDo    (print());
    }

    public static Request.Remove removeAttach(Response.FindOne attach) {
        return newInstance(Request.Remove.class).toBuilder()
                .path(attach.getPath())
                .name(attach.getName())
                .build();
    }
}
