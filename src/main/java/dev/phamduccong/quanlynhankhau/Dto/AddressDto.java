package dev.phamduccong.quanlynhankhau.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String province;
    private String district;
    private String ward;

    @Override
    public String toString() {
        return province + "-" + district + "-" + ward;
    }
}
