package com.damw.usersapp.service;

import com.damw.usersapp.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Map;

// Interface for UserService
public interface UserService {
    // USER CRUD OPERATIONS

    /**
     * Obtiene todos los usuarios.
     * 
     * @return List<User> con todos los usuarios.
     */
    List<User> getAllUsers();

    /**
     * Busca un usuario por su ID.
     * 
     * @param id Identificador del usuario.
     * @return Optional<User> con el usuario si existe, o vacío si no.
     */
    Optional<User> getUserById(Long id);

    /**
     * Crea un nuevo usuario.
     * 
     * @param user Usuario a crear.
     * @return User creado.
     */
    User save(User user);

    /**
     * Actualiza un usuario existente.
     * 
     * @param id   Identificador del usuario a actualizar.
     * @param user Usuario con los nuevos datos.
     * @return User actualizado.
     */
    User update(Long id, User user);

    /**
     * Actualiza parcialmente un usuario existente.
     * 
     * @param id      Identificador del usuario a actualizar.
     * @param updates Mapa con los campos a actualizar y sus nuevos valores.
     * @return User actualizado.
     */
    User patchUpdate(Long id, Map<String, Object> updates);

    /**
     * Elimina un usuario por su ID.
     * 
     * @param id Identificador del usuario a eliminar.
     */
    void delete(Long id);
}
