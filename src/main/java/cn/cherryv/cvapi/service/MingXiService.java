package cn.cherryv.cvapi.service;

import cn.cherryv.cvapi.model.Mingxi;
import cn.cherryv.cvapi.model.SaleMans;
import cn.cherryv.cvapi.model.Stroes;
import cn.cherryv.cvapi.model.WeiFa;

import java.util.List;

public interface MingXiService {
    List<Mingxi> selectMingXi();

    List<WeiFa> selectWeiFa();

    List<SaleMans> selectSaleMans();

    List<Stroes> selectStore();



}
