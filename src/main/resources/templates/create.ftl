<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product - Inventory System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 0;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto;
        }

        .card {
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            border: none;
            overflow: hidden;
        }

        .card-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
            padding: 12px 15px;
            border: 2px solid #e9ecef;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }

        .btn-submit {
            background: linear-gradient(135deg, #2ecc71, #27ae60);
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
            box-shadow: 0 8px 20px rgba(46, 204, 113, 0.4);
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

        .input-group-text {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 10px 0 0 10px;
        }

        .form-floating {
            margin-bottom: 20px;
        }

        .footer-text {
            text-align: center;
            margin-top: 30px;
            color: white;
            opacity: 0.9;
        }

        .icon-input {
            position: relative;
        }

        .icon-input i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #667eea;
            z-index: 5;
        }

        .icon-input .form-control {
            padding-left: 45px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <i class="bi bi-plus-circle"></i>
                    Add New Product
                </h2>
                <p>Fill in the details to add a new product to inventory</p>
            </div>
            <div class="card-body">
                <form name="inventory" action="create" method="post">
                    <div class="mb-4">
                        <label for="productName" class="form-label">
                            <i class="bi bi-tag"></i> Product Name
                        </label>
                        <div class="icon-input">
                            <i class="bi bi-box-seam"></i>
                            <input type="text"
                                   class="form-control"
                                   id="productName"
                                   name="productName"
                                   placeholder="Enter product name"
                                   required>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="stock" class="form-label">
                            <i class="bi bi-box"></i> Stock Quantity
                        </label>
                        <div class="icon-input">
                            <i class="bi bi-123"></i>
                            <input type="number"
                                   class="form-control"
                                   id="stock"
                                   name="stock"
                                   placeholder="Enter stock quantity"
                                   min="0"
                                   required>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="price" class="form-label">
                            <i class="bi bi-currency-dollar"></i> Price
                        </label>
                        <div class="icon-input">
                            <i class="bi bi-cash-coin"></i>
                            <input type="number"
                                   class="form-control"
                                   id="price"
                                   name="price"
                                   placeholder="Enter price"
                                   step="0.01"
                                   min="0"
                                   required>
                        </div>
                    </div>

                    <div class="row g-3 mt-4">
                        <div class="col-md-6">
                            <a href="/" class="btn btn-cancel">
                                <i class="bi bi-x-circle"></i> Cancel
                            </a>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-submit">
                                <i class="bi bi-check-circle"></i> Add Product
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="footer-text">
            <p><i class="bi bi-info-circle"></i> All fields are required</p>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
