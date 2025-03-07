package test.finance.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter<D,E,R> {

    private final BiFunction<R, E, E> toEntity;
    private final Function<E, D> toDto;

    public Converter(final BiFunction<R, E, E> toEntity, final Function<E, D> toDto) {
        this.toEntity = toEntity;
        this.toDto = toDto;
    }

    public final E toEntity(final R request, E entity) {
        return toEntity.apply(request, entity);
    }

    public final D toDto(final E entity) {return toDto.apply(entity);}

    public final List<D> toDto(final Collection<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public final Set<D> toDto(final Set<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public final Page<D> createFromEntities(final Page<E> entities) {
        return new PageImpl<D>(entities.stream().map(this::toDto).collect(Collectors.toList()),
                entities.getPageable(),
                entities.getTotalElements());
    }

    public final List<D> createFromEntities(final Collection<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
