package notes.project.logic.utils.mapper;

import liquibase.pro.packaged.M;
import notes.project.logic.model.Access;
import notes.project.logic.model.AccessMode;
import notes.project.logic.model.Client;
import notes.project.logic.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "note", source = "note")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "accessMode", source = "accessMode")
    Access to(Note note, Client client, AccessMode accessMode);
}
