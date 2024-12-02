package com.canvas.canvas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.canvas.canvas.model.CategoryType;
import com.canvas.canvas.model.Color;
import com.canvas.canvas.model.ItemType;
import com.canvas.canvas.model.PriceSettings;
import com.canvas.canvas.model.Size;
import com.canvas.canvas.model.Store;
import com.canvas.canvas.model.dto.PriceSettingsDto;
import com.canvas.canvas.repository.CategoryTypeRepository;
import com.canvas.canvas.repository.ColorRepository;
import com.canvas.canvas.repository.ItemTypeRepository;
import com.canvas.canvas.repository.PriceSettingsRepository;
import com.canvas.canvas.repository.SizeRepository;
import com.canvas.canvas.repository.StoreRepository;

@Service
public class PriceSettingsService {
	
	@Autowired
	private PriceSettingsRepository settings;
	
	@Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private CategoryTypeRepository categoryTypeRepository;

	public List<PriceSettings> getPriceSettings(String username) {
		return settings.findByStoreStoreOwner(username);
	}
	
	public void createNewSetting(PriceSettingsDto dto, String username) {
		PriceSettings setting = new PriceSettings();
		Store store = this.storeRepository.findByStoreOwner(username);
		if (store == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "user does not have a store");
		setting.setStore(store);
		Size size = this.sizeRepository.findByDescription(dto.getSizeDescription());
		setting.setSize(size);
		Color color = this.colorRepository.findByColorName(dto.getColorName());
		setting.setColor(color);
		ItemType type = this.itemTypeRepository.findByDescription(dto.getItemTypeDescription());
		if (type.getStore() != null && !type.getStore().getStoreName().equals(username)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user cannot user this type");
		setting.setItemType(type);
		CategoryType cat = this.categoryTypeRepository.findByDescription(dto.getCategoryTypeDescription());
		if (cat.getStore() != null && !cat.getStore().getStoreName().equals(username)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user cannot user this type");
		setting.setCategoryType(cat);
		setting.setDefaultPrice(dto.getDefaultPrice());
		this.settings.save(setting);
	}
}
