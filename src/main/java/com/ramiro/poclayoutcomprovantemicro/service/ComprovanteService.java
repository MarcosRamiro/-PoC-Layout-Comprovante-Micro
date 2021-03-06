package com.ramiro.poclayoutcomprovantemicro.service;

import com.ramiro.poclayoutcomprovantemicro.form.TipoVersao;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import io.reactivex.Maybe;

public interface ComprovanteService {

    Maybe<Comprovante> obterComprovantePorTipoEVersao(TipoVersao tipoVersao);

}
