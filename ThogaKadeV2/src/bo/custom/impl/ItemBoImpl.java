package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    private ItemDao dao=
            DaoFactory.getInstance().getDao(DaoFactory.DaoType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()));

    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return dao.delete(code);
    }

    @Override
    public ArrayList<ItemDto> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = dao.loadAll();
        ArrayList<ItemDto>dtoList=new ArrayList<>();
        for (Item i:items) {
            dtoList.add(new ItemDto(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand()));
        }
        return dtoList;
    }
}
