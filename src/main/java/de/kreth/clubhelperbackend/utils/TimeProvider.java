package de.kreth.clubhelperbackend.utils;

import java.time.LocalDateTime;

public interface TimeProvider {
   LocalDateTime getNow();
}
