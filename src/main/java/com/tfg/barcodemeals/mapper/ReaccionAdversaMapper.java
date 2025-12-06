package com.tfg.barcodemeals.mapper;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.model.ReaccionAdversa;

@Component
public class ReaccionAdversaMapper {
public ReaccionAdversaResponse toResponse(ReaccionAdversa reaccionAdversa) {
	if(reaccionAdversa == null) return null;
	return new ReaccionAdversaResponse(
		reaccionAdversa.getId(),
		reaccionAdversa.getNombre(),
		reaccionAdversa.getDescripcion()
		);
}

}
