package nc.opt.formation.exercice4spring.web;

import nc.opt.formation.exercice4spring.persitence.Pet;
import nc.opt.formation.exercice4spring.persitence.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PetController {
    @Autowired
    protected PetRepository petRepository;

    @RequestMapping(path = "/pet", method = RequestMethod.POST)
    public String createPet(@RequestBody Pet pet) {
        Integer id = petRepository.save(pet);
        return "saved " + id;
    }

    @RequestMapping(path= "/pets")
    public Iterable<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @RequestMapping(path = "/pet/{id}")
    public Optional<Pet> getPet(@PathVariable Integer id) {
        return Optional.of(petRepository.findById(id));
    }

    @RequestMapping(path = "/pet/{id}", method = RequestMethod.DELETE)
    public String deletePet(@PathVariable Integer id) {
        Integer oldId = petRepository.delete(id);
        return "removed " + oldId;
    }
}
