package com.tfg.barcodemeals.mapper;

import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.model.ReaccionAdversa;

public class ReaccionAdversaMapper {
public ReaccionAdversaResponse toResponse(ReaccionAdversa reaccionAdversa) {
	if(reaccionAdversa == null) return null;
	return new ReaccionAdversaResponse(
		reaccionAdversa.getId(),
		reaccionAdversa.getTipo().toString(),
		reaccionAdversa.getDescripcion()
		);
}

}
