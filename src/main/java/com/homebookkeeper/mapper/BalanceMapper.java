package com.homebookkeeper.mapper;

import com.homebookkeeper.DTO.BalanceDTO;
import com.homebookkeeper.model.Balance;
import com.homebookkeeper.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Component
public class BalanceMapper extends AbstractMapper<Balance, BalanceDTO> {

    private final ModelMapper mapper;
    private final UserRepo userRepo;

    public BalanceMapper(ModelMapper mapper, UserRepo userRepo) {
        super(Balance.class, BalanceDTO.class);
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void SetupMapper() {
        mapper.createTypeMap(Balance.class, BalanceDTO.class)
                .addMappings(m -> m.skip(BalanceDTO::setId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(BalanceDTO.class, Balance.class)
                .addMappings(m -> m.skip(Balance::setUser)).setPostConverter(toEntityConverter());
        mapper.typeMap(Balance.class, BalanceDTO.class).addMappings(m -> {
            m.map(Balance::getAmount, BalanceDTO::setValue);
            m.map(Balance::getBaseCurrency, BalanceDTO::setCurrency);
        });
        mapper.typeMap(BalanceDTO.class, Balance.class).addMappings(m -> {
            m.map(BalanceDTO::getValue, Balance::setAmount);
            m.map(BalanceDTO::getCurrency, Balance::setBaseCurrency);
        });
    }

    private Long getId(Balance source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }

    @Override
    void mapSpecificFields(BalanceDTO source, Balance destination) {
        destination.setUser(userRepo.findById(source.getId()).orElse(null));
    }

    @Override
    void mapSpecificFields(Balance source, BalanceDTO destination) {
        destination.setId(getId(source));
    }
}
