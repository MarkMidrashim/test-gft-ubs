package br.com.lacd.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.com.lacd.entities.Product;
import br.com.lacd.entities.Shopkeeper;

@Service
public class ProductSaleService {
	
	/* METHODS */
	
	/**
	 * Método responsável por dividir os produtos entre os lojistas
	 * @param products
	 * @param numberOfShopkeepers
	 * @return
	 */
	public List<Shopkeeper> divideStockProductPerShopkeepers(List<Product> products, Integer numberOfShopkeepers) {
		List<Shopkeeper> shopkeepers = new ArrayList<>();
		
		// Initialize
		for (int i = 0; i < numberOfShopkeepers; i++) {
			shopkeepers.add(new Shopkeeper());
		}
		
		shopkeepers.stream().forEach(shopkeeper -> {
			List<Product> product = new ArrayList<>();
			List<Double> prices = new ArrayList<>();
	
			products.stream().forEach(productItem -> {
				List<Integer> quantityPerShopkeeper = splitInteger(productItem.getQuantity(), numberOfShopkeepers);
				AtomicInteger indexForQuantityPerShopkeeper = new AtomicInteger(0);
				Integer productQuantity = quantityPerShopkeeper.get(indexForQuantityPerShopkeeper.getAndIncrement());
				
				product.add(new Product(
					productItem.getProduct(),
					productQuantity,
					productItem.getPrice(),
					productItem.getType(),
					productItem.getIndustry(),
					productItem.getOrigin()
				));
				
				prices.add(productItem.getPrice() * productQuantity);
			});
			
			if (shopkeeper.getProducts() == null) {
				shopkeeper.setProducts(new ArrayList<>());
				shopkeeper.setProducts(product);
			} else { 
				if (!shopkeeper.getProducts().equals(product)) shopkeeper.setProducts(product);
			}
			
			shopkeeper.setQuantity(shopkeeper.getProducts().stream().mapToInt(Product::getQuantity).sum());
			shopkeeper.setPrice(round(prices.stream().mapToDouble(value -> value).sum(), 3));
			shopkeeper.setAveragePrice(average(shopkeeper));
		});
		
		return shopkeepers;
	}
	
	/**
	 * Método responsável por realizar o cálculo de valor médio
	 * @param shopkeeper
	 * @return
	 */
	protected static Double average(Shopkeeper shopkeeper) {
		Double result = Double.NaN;
		
		if (shopkeeper.getPrice() > 0.0 && shopkeeper.getQuantity() > 0) {
			result = shopkeeper.getPrice() / shopkeeper.getQuantity();
		}
		
		return round(result, 3);
	}
	
	/**
	 * Método responsável por formatar o valor com 3 casas decimais
	 * @param value
	 * @param places
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Double round(Double value, int places) throws IllegalArgumentException {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * Método responsável por dividir igualmente a quantidade em partes iguais para os logistas
	 * @param number
	 * @param parts
	 * @return 
	 */
	protected List<Integer> splitInteger (Integer number, Integer parts) {
		List<Integer> values = new ArrayList<>();
		
		while (number > 0 && parts > 0) {
			Integer value = (int) Math.floor(number / parts);
			
		    if (value % 2 != 0) {
		        value = (int) Math.ceil(number / parts);
		    }

		    number -= value;
		    parts--;
		    values.add(value);
		}
		
		return values;
	}

}
