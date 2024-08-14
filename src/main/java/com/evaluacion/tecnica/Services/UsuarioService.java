package com.evaluacion.tecnica.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.tecnica.Daos.ICuentasDao;
import com.evaluacion.tecnica.Daos.IDireccionDaos;
import com.evaluacion.tecnica.Daos.IUsuarioDao;
import com.evaluacion.tecnica.Dtas.UsuarioDta;
import com.evaluacion.tecnica.Models.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDao usuariosDao;

    @Autowired
    private IDireccionDaos direccionesDao;

    @Autowired
    private ICuentasDao cuentasDao;

    public UsuarioDta save(UsuarioDta d) {

        Usuario u2 = this.usuariosDao.save(validate(d));

        UsuarioDta u = new UsuarioDta();
        u.setId(u2.getId());
        u.setNombre(u2.getNombre());
        u.setApellidoPaterno(u2.getApellidoPaterno());
        u.setApellidoMaterno(u2.getApellidoMaterno());
        u.setFechaNacimiento(u2.getFechaNacimiento());
        u.setId_cuenta(u2.getCuenta().getId());
        u.setId_direccion(u2.getDireccion().getId());

        return u;
    }

    public UsuarioDta findById(Integer id) {
        Optional<Usuario> usuarioOpt = this.usuariosDao.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario u2 = usuarioOpt.get();
            UsuarioDta u = new UsuarioDta();
            u.setId(u2.getId());
            u.setNombre(u2.getNombre());
            u.setApellidoPaterno(u2.getApellidoPaterno());
            u.setApellidoMaterno(u2.getApellidoMaterno());
            u.setFechaNacimiento(u2.getFechaNacimiento());
            u.setId_cuenta(u2.getCuenta().getId());
            u.setId_direccion(u2.getDireccion().getId());

            return u;
        }
        return null; // O lanza una excepción si prefieres manejarlo de esa manera
    }

    public void delete(Integer id) {
        this.usuariosDao.deleteById(id);
    }

    public Iterable<Usuario> findAll() {
        return this.usuariosDao.findAll();
    }

    public UsuarioDta update(UsuarioDta d) {
        Optional<Usuario> usuarioOpt = this.usuariosDao.findById(d.getId());
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            u.setNombre(d.getNombre());
            u.setApellidoPaterno(d.getApellidoPaterno());
            u.setApellidoMaterno(d.getApellidoMaterno());
            u.setFechaNacimiento(d.getFechaNacimiento());

            u.setCuenta(cuentasDao.findById(d.getId_cuenta()).get());
            u.setDireccion(direccionesDao.findById(d.getId_direccion()).get());

            Usuario u2 = this.usuariosDao.save(u);

            UsuarioDta uDto = new UsuarioDta();
            uDto.setId(u2.getId());
            uDto.setNombre(u2.getNombre());
            uDto.setApellidoPaterno(u2.getApellidoPaterno());
            uDto.setApellidoMaterno(u2.getApellidoMaterno());
            uDto.setFechaNacimiento(u2.getFechaNacimiento());
            uDto.setId_cuenta(u2.getCuenta().getId());
            uDto.setId_direccion(u2.getDireccion().getId());

            return uDto;
        }
        return null; // O lanza una excepción si prefieres manejarlo de esa manera
    }

    private Usuario validate(UsuarioDta t) {
        Usuario u = new Usuario();
        u.setNombre(t.getNombre());
        u.setApellidoPaterno(t.getApellidoPaterno());
        u.setApellidoMaterno(t.getApellidoMaterno());
        u.setFechaNacimiento(t.getFechaNacimiento());

        u.setCuenta(
                cuentasDao.findById(t.getId_cuenta()).orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));
        u.setDireccion(direccionesDao.findById(t.getId_direccion())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada")));

        return u;
    }

}
