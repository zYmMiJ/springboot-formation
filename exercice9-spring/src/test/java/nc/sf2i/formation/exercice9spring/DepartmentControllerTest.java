package nc.sf2i.formation.exercice9spring;

import nc.sf2i.formation.exercice9spring.dto.DepartmentDTO;
import nc.sf2i.formation.exercice9spring.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class DepartmentControllerTest {

    private final static String URI = "/api/departments";
    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDepartment() throws Exception {
        // 1- preparation du retour du mock
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(2);
        departmentDTO.setName("District 1");
        departmentDTO.setDescription("Produit de luxe");
        Optional<DepartmentDTO> optionalDepartment = Optional.of(departmentDTO);
        // 2- configuration du mock
        given(departmentService.findById(departmentDTO.getDepartmentId())).willReturn(optionalDepartment);
        // 3- exécution du test sur l'environnement simulé
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI+"/{department_id}", 2)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(2));
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        // 1- preparation du retour du mock
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(2);
        departmentDTO.setName("Aisne");
        departmentDTO.setDescription("desc");
        List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
        departments.add(departmentDTO);
        // 2- configuration du mock
        given(departmentService.getAll()).willReturn(departments);
        // 3- exécution du test
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(content().json("[{'departmentId':2, 'description':'desc', 'name':'Aisne'}]"));
    }
}
