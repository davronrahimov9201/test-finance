package test.finance.transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.finance.service.abstraction.TransactionService;
import test.finance.usecase.abstraction.TransactionUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping( "transaction")
@Tag(name = "Transaction", description = "Транзакция")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase useCase;
    private final TransactionService service;
    private final TransactionConverter converter;

    @PostMapping
    @Operation(summary = "Создать")
    public ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionRequest request) {
        TransactionEntity model = useCase.create(converter.toEntity(request, new TransactionEntity()));
        service.refresh(model);
        return ResponseEntity.ok(converter.toDto(model));
    }

    @GetMapping("pageable")
    @Operation(summary = "Постраничный")
    public ResponseEntity<Page<TransactionDto>> pageable(
        @RequestParam(required = false) UUID userId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        Pageable pageable
    ) {
        return ResponseEntity.ok(converter.createFromEntities(service.getTransactions(userId, startDate, endDate, pageable)));
    }



}
