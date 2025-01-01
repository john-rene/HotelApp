package testCode.convertor;


import testCode.entity.ReservationEntity;
import testCode.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

    @Override
    public ReservationResponse convert(ReservationEntity source) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        if (null != source.getRoomEntity())
            reservationResponse.setId(source.getRoomEntity().getId());

        return reservationResponse;
    }
}