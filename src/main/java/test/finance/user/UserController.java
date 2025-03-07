package test.finance.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.finance.service.abstraction.UserService;

import java.util.UUID;

@RestController
@RequestMapping( "user")
@Tag(name = "User", description = "Пользователь")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserConverter converter;

    @PostMapping
    @Operation(summary = "Создать")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(converter.toDto(service.create(converter.toEntity(request, new UserEntity()))));
    }

    @PutMapping("{id}")
    @Operation(summary = "Изменить")
    public ResponseEntity<UserDto> create(@PathVariable UUID id, @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(converter.toDto(service.update(converter.toEntity(request, service.getById(id)))));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить")
    public ResponseEntity<UserDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(converter.toDto(service.getById(id)));
    }

    @GetMapping("pageable")
    @Operation(summary = "Постраничный")
    public ResponseEntity<Page<UserDto>> pageable(Pageable pageable) {
        return ResponseEntity.ok(converter.createFromEntities(service.findAll(pageable)));
    }

}
