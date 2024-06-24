package br.com.unifood.unifood.service;

import br.com.unifood.unifood.model.address.Address;
import br.com.unifood.unifood.model.dto.SupplierDTO;
import br.com.unifood.unifood.model.suppliers.Suppliers;
import br.com.unifood.unifood.repository.AddressRepository;
import br.com.unifood.unifood.repository.SupplierRepository;
import br.com.unifood.unifood.utils.CnpjUtils;
import br.com.unifood.unifood.utils.ZipCodeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     *
     * @return List<Suppliers>
     */
    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    /**
     *
     * @param id int
     * @return Optional<Suppliers>
     */
    public Suppliers getById(int id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    /**
    *
    * @param supplierDTO SupplierDTO
    * @return Suppliers
    */
    @Transactional
    public Suppliers save(SupplierDTO supplierDTO) {

        Address address = extractAddressFromDTO(supplierDTO);
        validateAddress(address);

        String cleanedCnpj = CnpjUtils.cleanCnpj(supplierDTO.cnpj());

        if (supplierDTO.id() == 0) {

            Suppliers supplier = new Suppliers(
                supplierDTO.name(),
                supplierDTO.company_name(),
                supplierDTO.email(),
                cleanedCnpj,
                supplierDTO.telephone(),
                null
            );

            if (supplier.getCnpj() != null && !supplier.getCnpj().isEmpty()) {
                Suppliers supplierWithSameCnpj = supplierRepository.findByCnpj(cleanedCnpj);
                if (supplierWithSameCnpj != null) {
                    throw new RuntimeException("Supplier with this CNPJ already exists");
                }
            }else{
                throw new RuntimeException("CNPJ cannot be null");
            }

            Address savedAddress = addressRepository.save(address);
            supplier.setAddress(savedAddress);

            return supplierRepository.save(supplier);

        }
        else {
            Suppliers existingSupplier = getById(supplierDTO.id());

            if (!existingSupplier.getCnpj().equals(supplierDTO.cnpj())) {

                Suppliers supplierWithSameCnpj = supplierRepository.findByCnpj(cleanedCnpj);
                if (supplierWithSameCnpj != null && !supplierWithSameCnpj.equals(existingSupplier)) {
                    throw new RuntimeException("Supplier with this CNPJ already exists");
                }
            }

            Address existingAddress = existingSupplier.getAddress();

            existingSupplier.setName(supplierDTO.name());
            existingSupplier.setCompanyName(supplierDTO.company_name());
            existingSupplier.setEmail(supplierDTO.email());
            existingSupplier.setCnpj(cleanedCnpj);
            existingSupplier.setTelephone(supplierDTO.telephone());

            existingAddress.setStreet(address.getStreet());
            existingAddress.setNumber(address.getNumber());
            existingAddress.setComplement(address.getComplement());
            existingAddress.setPostalCode(address.getPostalCode());

            return supplierRepository.save(existingSupplier);
        }
    }

    /**
     *
     * @param id int
     */
    @Transactional
    public void deleteBydId(int id) {
        Suppliers supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Address address = supplier.getAddress();

        supplierRepository.deleteById(id);

        if (address != null) {
            addressRepository.delete(address);
        }
    }


    /**
     *
     * @param cnpj String
     * @return Suppliers
     */
    public Suppliers getByCnpj(String cnpj){
        return supplierRepository.findByCnpj(cnpj);
    }

    private Address extractAddressFromDTO(SupplierDTO supplierDTO) {
        return new Address(
            supplierDTO.address().street(),
            supplierDTO.address().number(),
            supplierDTO.address().complement(),
            ZipCodeUtil.cleanCep(supplierDTO.address().postal_code())
        );
    }

    private void validateAddress(Address address) {
        if (!ZipCodeUtil.isValidCep(address.getPostalCode())) {
            throw new RuntimeException("Invalid Postal Code");
        }
    }
}
