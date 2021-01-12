package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.DescriptionDao;
import com.spring.model.dao.RoomDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.RoomForm;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.RoomService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoomServiceException;
import com.spring.model.service.exceptions.RoomWithThisNumberIsExist;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RoomServiceImpl implements RoomService {

    private DescriptionDao descriptionDao;
    private RoomDao roomDao;

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void setDescriptionDao(DescriptionDao descriptionDao) {
        this.descriptionDao = descriptionDao;
    }

    @Override
    public Room readById(long id, boolean withDesc) throws RoomServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = roomDao.findById(id, entityManager);
            if (room != null) {
                if (withDesc) room.getDescriptions().size();
                entityManager.getTransaction().commit();
                return room;
            } else throw new EntityNotFoundException("resource not found");
        } catch (IllegalArgumentException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getCause().toString());
            throw new RoomServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Room> readAll(boolean withDesc) throws RoomServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Room> rooms = roomDao.readAll(entityManager);
            if (withDesc) {
                for (Room room : rooms) {
                    room.getDescriptions().size();
                }
            }
            entityManager.getTransaction().commit();
            return rooms;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            throw new RoomServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Room createRoom(RoomForm roomForm) throws RoomServiceException, ValidationException, RoomWithThisNumberIsExist {
        if (roomForm != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<RoomForm>> violations = validator.validate(roomForm);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    Room room = new Room();
                    room.setNumber(roomForm.getNumber());
                    room.setPrice(roomForm.getPrice());
                    room.setRoomClass(roomForm.getRoomClass());
                    room.setSits(roomForm.getSits());
                    Room equalRoom = roomDao.findByRoomNumber(roomForm.getNumber(), entityManager);
                    if (equalRoom == null) {
                        if (roomForm.getDescriptions() != null && roomForm.getDescriptions().size() > 0) {
                            Set<Description> roomDesc = new HashSet<>();
                            for (String description : roomForm.getDescriptions()) {
                                roomDesc.add(descriptionDao.findDescriptionByName(description, entityManager));
                            }
                            room.setDescriptions(roomDesc);
                        }
                        room = roomDao.createEntity(room, entityManager);
                        entityManager.getTransaction().commit();
                        return room;
                    } else {
                        entityManager.getTransaction().commit();
                        throw new RoomWithThisNumberIsExist("room with such number is already exist");
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new RoomServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> validationErrors = new ArrayList<>();
                for (ConstraintViolation<RoomForm> violation : violations) {
                    validationErrors.add(violation.getMessage());
                }
                validError.setErrors(validationErrors);
                throw new ValidationException("validError", validError);
            }
        } else throw new ValidationException("can not be null");
    }

    @Override
    public void delete(long id) throws RoomServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = roomDao.findById(id, entityManager);
            if (room != null) {
                roomDao.delete(room, entityManager);
                entityManager.getTransaction().commit();
            } else throw new EntityNotFoundException("resource not found");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            throw new RoomServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Room update(RoomForm roomForm, long roomId) throws RoomServiceException, EntityNotFoundException, ValidationException {
        if (roomForm != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<RoomForm>> violations = validator.validate(roomForm);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    Room room = roomDao.findById(roomId, entityManager);
                    if (room != null) {
                        if (roomForm.getDescriptions() != null) {
                            Set<Description> roomDesc = new HashSet<>();
                            for (String description : roomForm.getDescriptions()) {
                                roomDesc.add(descriptionDao.findDescriptionByName(description, entityManager));
                            }
                            room.setDescriptions(roomDesc);
                        }
                        room.setSits(roomForm.getSits());
                        room.setRoomClass(roomForm.getRoomClass());
                        room.setPrice(roomForm.getPrice());
                        room.setNumber(roomForm.getNumber());
                        room = roomDao.updateEntity(room, entityManager);
                        entityManager.getTransaction().commit();
                        return room;
                    } else throw new EntityNotFoundException("resource not found");
                } catch (IllegalArgumentException | TransactionRequiredException e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new RoomServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> validationErrors = new ArrayList<>();
                for (ConstraintViolation<RoomForm> violation : violations) {
                    validationErrors.add(violation.getMessage());
                }
                validError.setErrors(validationErrors);
                throw new ValidationException("validError", validError);
            }
        } else throw new ValidationException("can not be null");

    }
}
