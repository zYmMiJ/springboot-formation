package nc.opt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import nc.opt.web.rest.TestUtil;

public class CommentaryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Commentary.class);
        Commentary commentary1 = new Commentary();
        commentary1.setId(1L);
        Commentary commentary2 = new Commentary();
        commentary2.setId(commentary1.getId());
        assertThat(commentary1).isEqualTo(commentary2);
        commentary2.setId(2L);
        assertThat(commentary1).isNotEqualTo(commentary2);
        commentary1.setId(null);
        assertThat(commentary1).isNotEqualTo(commentary2);
    }
}
