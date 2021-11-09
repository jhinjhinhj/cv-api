package cn.cherryv.cvapi.mapper;

import cn.cherryv.cvapi.model.Mingxi;
import cn.cherryv.cvapi.model.SaleMans;
import cn.cherryv.cvapi.model.Stroes;
import cn.cherryv.cvapi.model.WeiFa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MingXiMapper {
    List<Mingxi> selectMingXi();

    List<WeiFa> selectWeiFa();

    Long CountMoney();

    List<SaleMans> selectSaleMans();

    List<Stroes> selectStore();
}
