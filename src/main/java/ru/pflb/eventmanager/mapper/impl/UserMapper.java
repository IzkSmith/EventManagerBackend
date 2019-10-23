package ru.pflb.eventmanager.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.AbstractMapper;
import ru.pflb.eventmanager.mapper.Mapper;
import ru.pflb.eventmanager.repository.RoleRepository;
import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {

    private final RoleRepository roleRepository;

    @Autowired
    public UserMapper( RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setRoles)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setRoles)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(User source, UserDto destination) {
        if (source.getRoles() != null) {
            destination.getRoles().addAll(source.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        }
    }

    @Override
    protected void mapSpecificFields(UserDto source, User destination) {
        if (source.getRoles() != null) {
            destination.getRoles().addAll(
                    source.getRoles()
                            .stream()
                            .map(l -> roleRepository.findById(l).orElse(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())
            );
        }
    }
}