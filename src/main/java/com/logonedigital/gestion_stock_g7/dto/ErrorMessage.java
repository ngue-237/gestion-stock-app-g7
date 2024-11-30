package com.logonedigital.gestion_stock_g7.dto;

import java.util.Date;

public record ErrorMessage(int status,
                           String msg,
                           String error,
                           Date timestamp) {
}
