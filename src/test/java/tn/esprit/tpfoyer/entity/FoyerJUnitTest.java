package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoyerJUnitTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFoyer;

    String nomFoyer;
    long capaciteFoyer;

    @OneToOne(mappedBy = "foyer")
    @ToString.Exclude
    @JsonIgnore
    Universite universite;

    @OneToMany(mappedBy = "foyer")
    @JsonIgnore
    @ToString.Exclude
    Set<Bloc> blocs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foyer)) return false;
        Foyer foyer = (Foyer) o;
        return capaciteFoyer == foyer.getCapaciteFoyer() &&
                Objects.equals(idFoyer, foyer.getIdFoyer()) &&
                Objects.equals(nomFoyer, foyer.getNomFoyer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFoyer, nomFoyer, capaciteFoyer);
    }
}

