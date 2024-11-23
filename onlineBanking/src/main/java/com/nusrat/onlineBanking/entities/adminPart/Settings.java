package com.nusrat.onlineBanking.entities.adminPart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "setting_key", nullable = false, unique = true)
    private String key; // E.g., "MaxLoanLimit", "TransactionFeeRate"

    @Column(nullable = false)
    private String value; // E.g., "100000", "0.02"

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isEditable; // Indicates if the setting can be changed by admins
}
