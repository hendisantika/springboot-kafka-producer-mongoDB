<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Stock - Inventory System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 0;
        }

        .form-container {
            max-width: 700px;
            margin: 0 auto;
        }

        .card {
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            border: none;
            overflow: hidden;
        }

        .card-header {
            background: linear-gradient(135deg, #3498db, #2980b9);
            color: white;
            padding: 30px;
            border: none;
        }

        .card-header h2 {
            margin: 0;
            font-weight: 700;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .card-header p {
            margin: 10px 0 0 0;
            opacity: 0.95;
        }

        .card-body {
            padding: 40px;
        }

        .info-section {
            background: linear-gradient(135deg, #e8f4f8, #d4edfa);
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 30px;
            border-left: 5px solid #3498db;
        }

        .info-section h5 {
            color: #2980b9;
            margin-bottom: 15px;
            font-weight: 700;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .info-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid rgba(52, 152, 219, 0.2);
        }

        .info-item:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 600;
            color: #495057;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .info-value {
            font-weight: 700;
            color: #2980b9;
        }

        .form-label {
            font-weight: 600;
            color: #495057;
            margin-bottom: 8px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .form-control {
            border-radius: 10px;
            padding: 15px 20px;
            border: 2px solid #e9ecef;
            transition: all 0.3s ease;
            font-size: 18px;
            font-weight: 600;
        }

        .form-control:focus {
            border-color: #3498db;
            box-shadow: 0 0 0 0.2rem rgba(52, 152, 219, 0.25);
        }

        .btn-submit {
            background: linear-gradient(135deg, #3498db, #2980b9);
            color: white;
            padding: 12px 40px;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            transition: all 0.3s ease;
            width: 100%;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(52, 152, 219, 0.4);
        }

        .btn-cancel {
            background: #6c757d;
            color: white;
            padding: 12px 40px;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            transition: all 0.3s ease;
            width: 100%;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .btn-cancel:hover {
            background: #5a6268;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(108, 117, 125, 0.4);
            color: white;
        }

        .stock-input-wrapper {
            position: relative;
            margin-bottom: 20px;
        }

        .stock-input-wrapper i {
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
            color: #3498db;
            font-size: 20px;
            z-index: 5;
        }

        .stock-input-wrapper .form-control {
            padding-left: 55px;
        }

        .footer-text {
            text-align: center;
            margin-top: 30px;
            color: white;
            opacity: 0.9;
        }

        .badge-custom {
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            padding: 8px 15px;
            border-radius: 20px;
            font-weight: 600;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <i class="bi bi-box-seam"></i>
                    Update Stock
                </h2>
                <p>Adjust the stock quantity for this product</p>
            </div>
            <div class="card-body">
                <div class="info-section">
                    <h5>
                        <i class="bi bi-info-circle"></i>
                        Product Information
                    </h5>
                    <div class="info-item">
                            <span class="info-label">
                                <i class="bi bi-tag"></i> Product Name
                            </span>
                        <span class="info-value">${inventory.productName}</span>
                    </div>
                    <div class="info-item">
                            <span class="info-label">
                                <i class="bi bi-box"></i> Current Stock
                            </span>
                        <span class="info-value">
                                <span class="badge-custom">${inventory.stock} units</span>
                            </span>
                    </div>
                    <div class="info-item">
                            <span class="info-label">
                                <i class="bi bi-currency-dollar"></i> Price
                            </span>
                        <span class="info-value">$${inventory.price?string["0.00"]}</span>
                    </div>
                </div>

                <form name="inventory" action="/updateStock" method="post">
                    <input type="hidden" name="id" value="${inventory.id}">

                    <div class="mb-4">
                        <label for="stock" class="form-label">
                            <i class="bi bi-arrow-up-circle"></i> New Stock Quantity
                        </label>
                        <div class="stock-input-wrapper">
                            <i class="bi bi-123"></i>
                            <input type="number"
                                   class="form-control"
                                   id="stock"
                                   name="stock"
                                   value="${inventory.stock}"
                                   min="0"
                                   required
                                   autofocus>
                        </div>
                        <small class="text-muted">
                            <i class="bi bi-lightbulb"></i> Enter the new stock quantity for this product
                        </small>
                    </div>

                    <div class="row g-3 mt-4">
                        <div class="col-md-6">
                            <a href="/" class="btn btn-cancel">
                                <i class="bi bi-x-circle"></i> Cancel
                            </a>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-submit">
                                <i class="bi bi-check-circle"></i> Update Stock
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="footer-text">
            <p><i class="bi bi-shield-check"></i> Changes will be reflected immediately in the system</p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Auto-select the input field value for easier editing
    document.getElementById('stock').select();
</script>
</body>
</html>
